import ProductCard from "./ProductCard";
import "../../styles/FeaturedProducts.css";

function FeaturedProducts() {

    const products = [

        {
            id:1,
            name:"iPhone 15",
            price:79999,
            image:"https://via.placeholder.com/250"
        },

        {
            id:2,
            name:"MacBook Air",
            price:99999,
            image:"https://via.placeholder.com/250"
        },

        {
            id:3,
            name:"Samsung S24",
            price:69999,
            image:"https://via.placeholder.com/250"
        },

        {
            id:4,
            name:"Sony Headphones",
            price:14999,
            image:"https://via.placeholder.com/250"
        }

    ];

    return (

        <section className="featured-products">

            <h2>Featured Products</h2>

            <div className="products-grid">

                {
                    products.map(product => (

                        <ProductCard
                            key={product.id}
                            product={product}
                        />

                    ))
                }

            </div>

        </section>

    );

}

export default FeaturedProducts;