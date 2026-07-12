import "../../styles/Hero.css";

function Hero() {
  return (
    <section className="hero">
      <div className="hero-content">
        <h1>Welcome to ShopSharper</h1>
        <p>
          Discover the latest electronics, fashion, and lifestyle products at
          the best prices.
        </p>
        <button>Shop now</button>
      </div>
      <div className="hero-image">
        <img
          src="https://images.unsplash.com/photo-1607082349566-187342175e2f?w=800"
          alt="Shopping"
        />
      </div>
    </section>
  );
}
export default Hero;
