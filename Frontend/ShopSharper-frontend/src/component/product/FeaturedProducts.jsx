import { useEffect, useState } from "react";
import "../../styles/FeaturedProducts.css";
import { getAllProduct } from "../services/productService";
import ProductCard from "./ProductCard";

function FeaturedProducts() {
  const [product, setProduct] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    fetchProducts();
  }, []);

  async function fetchProducts() {
    try {
      const response = await getAllProduct();
      setProduct(response.data.data.content);
    } catch (err) {
      setError("unable to load products");
    } finally {
      setLoading(false);
    }
  }

  if (loading) return <h2>Loading.....</h2>;
  if (error) return <h2>{error}</h2>;
  return (
    <section className="featured-products">
      <h2>Featured Products</h2>
      <div className="products-grid">
        {product.map((p) => (
          <ProductCard key={p.id} product={p} />
        ))}
      </div>
    </section>
  );
}

export default FeaturedProducts;
