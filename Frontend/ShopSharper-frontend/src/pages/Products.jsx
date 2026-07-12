import ProductList from "../component/product/ProductList";

function Products(){

    const products =[
        {
            id:1,
            name:"Laptop",
            price:55000,
            imageUrl:"https://via.placeholder.com/200"
        },
        {
            id:2,
            name:"Phone",
            price:25000,
            imageUrl:"https://via.placeholder.com/201"
        },
        {
            id:3,
            name:"CPU",
            price:45000,
            imageUrl:"https://via.placeholder.com/203"
        }

    ];
    return (
        <ProductList  products={products}/>
    );
}
export default Products;