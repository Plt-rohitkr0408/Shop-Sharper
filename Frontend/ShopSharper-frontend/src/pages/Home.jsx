import CategorySection from "../component/home/CategorySection";
import Hero from "../component/home/Hero";
import FeaturedProducts from "../component/product/FeaturedProducts";
import "../styles/Home.css";
function Home() {
  return (
    <>
      <Hero />

      <CategorySection />
      <FeaturedProducts />
    </>
  );
}

export default Home;
