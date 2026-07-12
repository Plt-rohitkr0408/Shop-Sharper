import "../../styles/Categorysection.css";
function CategorySection() {

    const categories = [
        "Mobiles",
        "Laptops",
        "Fashion",
        "Watches",
        "Headphones",
        "Accessories"
    ];

    return (

        <section className="category-section">

            <h2>Shop by Category</h2>

            <div className="category-grid">

                {
                    categories.map((category) => (

                        <div
                            className="category-card"
                            key={category}
                        >

                            <h3>{category}</h3>

                        </div>

                    ))
                }

            </div>

        </section>

    );

}

export default CategorySection;