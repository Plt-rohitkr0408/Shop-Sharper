import API from "./api"

export const getAllProduct=()=>{
    return API.get("/products");
};

export const getProductById =(id)=>{
    return API.get(`/products/${id}`);
};