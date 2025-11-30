import React, { useEffect, useState, useMemo } from "react";
import { Input, Tree, Spin, Empty } from 'antd';
import { SearchOutlined, DownOutlined, RightOutlined } from '@ant-design/icons';
import { getBreeds } from "../services/petService";
import { PET_TYPES } from "../utils/constants";
import "./BreedSidebar.css";

const BreedSidebar = ({ petType, selectedBreed, onBreedSelect }) => {
    const [breeds, setBreeds] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [searchQuery, setSearchQuery] = useState('');
    const [expandedKeys, setExpandedKeys] = useState([]);
    const [autoExpandParent, setAutoExpandParent] = useState(true);

    useEffect(() => {
        loadBreeds();
        setSearchQuery('');
        setExpandedKeys([]);
    }, [petType]);

    const loadBreeds = async () => {
        setLoading(true);
        setError(null);
        try {
            const data = await getBreeds(petType);
            // Filtrar razas sin datos válidos
            const validBreeds = data.filter(breed => {
                if (petType === PET_TYPES.DOGS) {
                    return breed && breed.breed && breed.breed.trim() !== '';
                } else {
                    return breed && breed.id && breed.name;
                }
            });
            setBreeds(validBreeds);
        } catch (err) {
            setError(err.message);
        } finally {
            setLoading(false);
        }
    }
    
    const getBreedName = (breed) => {
        if(petType === PET_TYPES.DOGS){
            return breed.breed;
        } else {
            return breed.name;
        }
    }

    const getBreedId = (breed) => {
        if(petType === PET_TYPES.DOGS){
            return breed.breed;
        } else {
            return breed.id;
        }
    }

    const formatBreedName = (name) => {
        if (!name || typeof name !== 'string') {
            return '';
        }
        return name
            .split('-')
            .map(word => word.charAt(0).toUpperCase() + word.slice(1))
            .join(' ');
    };

    const treeData = useMemo(() => {
        if (petType === PET_TYPES.DOGS) {
            return breeds
                .filter(breed => breed && breed.breed)
                .map(breed => {
                    const breedId = breed.breed;
                    const breedName = formatBreedName(breed.breed);
                    const hasSubBreeds = breed.subBreed && Array.isArray(breed.subBreed) && breed.subBreed.length > 0;
                    
                    const node = {
                        title: breedName || breedId,
                        key: `breed-${breedId}`,
                        breedId: breedId,
                        isBreed: true,
                    };

                    if (hasSubBreeds) {
                        node.children = breed.subBreed
                            .filter(subBreed => subBreed && typeof subBreed === 'string')
                            .map(subBreed => ({
                                title: formatBreedName(subBreed) || subBreed,
                                key: `subbreed-${breedId}-${subBreed}`,
                                breedId: breedId,
                                subBreedId: subBreed,
                                isSubBreed: true,
                            }));
                    }

                    return node;
                });
        } else {
            return breeds
                .filter(breed => breed && breed.id && breed.name)
                .map(breed => ({
                    title: breed.name,
                    key: `breed-${breed.id}`,
                    breedId: breed.id,
                    isBreed: true,
                }));
        }
    }, [breeds, petType]);

    const filteredTreeData = useMemo(() => {
        if (!searchQuery.trim()) {
            return treeData;
        }

        const filterTree = (nodes) => {
            return nodes.reduce((acc, node) => {
                const title = node.title.toLowerCase();
                const query = searchQuery.toLowerCase();
                
                if (title.includes(query)) {
                    acc.push(node);
                } else if (node.children) {
                    const filteredChildren = filterTree(node.children);
                    if (filteredChildren.length > 0) {
                        acc.push({
                            ...node,
                            children: filteredChildren,
                        });
                    }
                }
                return acc;
            }, []);
        };

        const filtered = filterTree(treeData);
        
        if (filtered.length > 0 && searchQuery.trim()) {
            const keys = [];
            const collectKeys = (nodes) => {
                nodes.forEach(node => {
                    if (node.children && node.children.length > 0) {
                        keys.push(node.key);
                        collectKeys(node.children);
                    }
                });
            };
            collectKeys(filtered);
            setExpandedKeys(keys);
            setAutoExpandParent(true);
        }

        return filtered;
    }, [treeData, searchQuery]);

    const findNodeByKey = (nodes, key) => {
        for (const node of nodes) {
            if (node.key === key) {
                return node;
            }
            if (node.children) {
                const found = findNodeByKey(node.children, key);
                if (found) return found;
            }
        }
        return null;
    };

    const onSelect = (selectedKeys) => {
        if (selectedKeys.length === 0) {
            onBreedSelect(null);
            return;
        }
        
        const selectedKey = selectedKeys[0];
        const node = findNodeByKey(filteredTreeData, selectedKey);
        
        if (!node) {
            onBreedSelect(null);
            return;
        }
        
        if (node.isSubBreed) {
            onBreedSelect(`${node.breedId}/${node.subBreedId}`);
        } else {
            onBreedSelect(node.breedId);
        }
    };

    const onExpand = (expandedKeysValue) => {
        setExpandedKeys(expandedKeysValue);
        setAutoExpandParent(false);
    };

    const getSelectedKeys = useMemo(() => {
        if (!selectedBreed) return [];
        
        if (petType === PET_TYPES.DOGS && selectedBreed.includes('/')) {
            const [breedId, subBreedId] = selectedBreed.split('/');
            return [`subbreed-${breedId}-${subBreedId}`];
        } else {
            return [`breed-${selectedBreed}`];
        }
    }, [selectedBreed, petType]);

    if(loading){
        return (
            <div className="breed-sidebar">
                <Spin size="large" style={{ display: 'flex', justifyContent: 'center', padding: '40px 0' }} />
            </div>
        );
    }
    
    if(error){
        return (
            <div className="breed-sidebar">
                <Empty description="Error al cargar las razas" />
            </div>
        );
    }

    return (
        <div className="breed-sidebar">
            <Input.Search
                placeholder="Buscar raza"
                value={searchQuery}
                onChange={(e) => setSearchQuery(e.target.value)}
                prefix={<SearchOutlined />}
                allowClear
                style={{ marginBottom: 16 }}
            />
            
            {filteredTreeData.length === 0 ? (
                <Empty description="No se encontraron razas" />
            ) : (
                <Tree
                    showLine={false}
                    showIcon={false}
                    defaultExpandAll={false}
                    expandedKeys={expandedKeys}
                    autoExpandParent={autoExpandParent}
                    selectedKeys={getSelectedKeys}
                    onSelect={onSelect}
                    onExpand={onExpand}
                    treeData={filteredTreeData}
                    switcherIcon={({ expanded }) => 
                        expanded ? <DownOutlined /> : <RightOutlined />
                    }
                    className="breed-tree"
                />
            )}
        </div>
    );
};

export default BreedSidebar;