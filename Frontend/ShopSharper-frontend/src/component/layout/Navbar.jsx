import { Link } from "react-router-dom";
import "../../styles/Navbar.css";

function Navbar() {
  return (
    <header className="navbar">
      <div className="logo">
        <Link to="/">ShopSharper</Link>
      </div>

      <nav>
        <ul className="nav-links">
          <li>
            <Link to="/">Home</Link>
          </li>

          <li>
            <Link to="/products">Products</Link>
          </li>

          <li>
            <Link to="/cart">Cart</Link>
          </li>

          <li>
            <Link to="/login">Login</Link>
          </li>
        </ul>
      </nav>
      <div className="search-box">
        <input type="text" placeholder="Search products..." />
      </div>
    </header>
  );
}

export default Navbar;
