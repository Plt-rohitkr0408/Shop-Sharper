function ProductCard({ product }) {
  return (
    <div className="produt-card">
      <img src={product.imageUrl} alt={product.name} />
      <h3>{product.name} </h3>
      <p>{product.price}</p>
      <button>Add to Cart</button>
    </div>
  );
}

export default ProductCard;