import { Routes, Route } from "react-router-dom";
import Navbar from "./component/layout/Navbar";
import Footer from "./component/layout/Footer";
import Home from "./pages/Home";
import Products from "./pages/Products";
import Login from "./component/auth/Login";
import Cart from "./pages/Cart";
import Register from "./component/auth/Register"

function App() {

  return (
    <>

      <Navbar />

      <Routes>

        <Route path="/" element={<Home />} />

        <Route path="/products" element={<Products />} />

        <Route path="/cart" element={<Cart />} />

        <Route path="/login" element={<Login />} />

        <Route path="/register" element={<Register />} />

      </Routes>
      
      <Footer />

    </>
  );

}

export default App;