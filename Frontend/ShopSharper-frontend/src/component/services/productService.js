import API from "./api"

export const getAllProduct=()=>{
    return API.get("/products");
};

export const getProductById =(id)=>{
    return API.get(`/products/${id}`);
};

export const searchProducts = (keyword) => {
    return API.get(`/products/search?keyword=${keyword}`);
};