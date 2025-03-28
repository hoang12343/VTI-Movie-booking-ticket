// components/LoginModal.jsx
import React, { useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import { closeLoginModal, openRegisterModal } from "../redux/slices/modalSlice";
import { validateLogin } from "../utils/Validation";
import { IoIosEyeOff, IoMdEye } from "react-icons/io";

const LoginModal = () => {
  const { isLoginOpen } = useSelector((state) => state.modal);
  const dispatch = useDispatch();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [showPassword, setShowPassword] = useState(false);
  const [errors, setErrors] = useState({});

  const handleSubmit = (e) => {
    e.preventDefault();
    const validationErrors = validateLogin(email, password);

    if (Object.keys(validationErrors).length > 0) {
      setErrors(validationErrors);
      return;
    }

    console.log("Đăng nhập với:", { email, password });
    setErrors({});
    setEmail("");
    setPassword("");
    dispatch(closeLoginModal());
  };

  if (!isLoginOpen) return null;

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
      <div className="bg-white rounded-lg p-6 w-full max-w-md relative">
        <button
          onClick={() => dispatch(closeLoginModal())}
          className="absolute top-2 right-2 text-gray-600 hover:text-gray-800"
        >
          <svg
            className="w-6 h-6"
            fill="none"
            stroke="currentColor"
            viewBox="0 0 24 24"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
              strokeLinecap="round"
              strokeLinejoin="round"
              strokeWidth="2"
              d="M6 18L18 6M6 6l12 12"
            ></path>
          </svg>
        </button>
        <h2 className="text-2xl font-bold text-gray-800 text-center mb-6">
          Đăng Nhập
        </h2>
        <form onSubmit={handleSubmit}>
          <div className="mb-4">
            <label
              htmlFor="email"
              className="block text-gray-700 font-semibold mb-2"
            >
              Email
            </label>
            <input
              type="email"
              id="email"
              value={email}
              onChange={(e) => {
                setEmail(e.target.value);
                setErrors((prev) => ({ ...prev, email: "" }));
              }}
              className="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-orange-500"
              placeholder="Nhập email của bạn"
            />
            {errors.email && (
              <p className="text-red-500 text-sm mt-1">{errors.email}</p>
            )}
          </div>
          <div className="mb-6 relative">
            <label
              htmlFor="password"
              className="block text-gray-700 font-semibold mb-2"
            >
              Mật khẩu
            </label>
            <input
              type={showPassword ? "text" : "password"}
              id="password"
              value={password}
              onChange={(e) => {
                setPassword(e.target.value);
                setErrors((prev) => ({ ...prev, password: "" }));
              }}
              className="w-full px-4 py-2 pr-12 border rounded-md focus:outline-none focus:ring-2 focus:ring-orange-500" // Tăng pr-10 thành pr-12
              placeholder="Nhập mật khẩu"
            />
            <button
              type="button"
              onClick={() => setShowPassword(!showPassword)}
              className="absolute right-3 top-7 transform translate-y-1/2 text-gray-600 flex items-center justify-center"
            >
              {showPassword ? (
                <IoMdEye className="w-6 h-6" />
              ) : (
                <IoIosEyeOff className="w-6 h-6" />
              )}
            </button>
            {errors.password && (
              <p className="text-red-500 text-sm mt-1">{errors.password}</p>
            )}
          </div>
          <button
            type="submit"
            className="w-full bg-orange-500 text-white py-2 rounded-md hover:bg-orange-600 transition-colors duration-300"
          >
            Đăng Nhập
          </button>
        </form>
        <p className="text-center text-gray-600 mt-4">
          Chưa có tài khoản?{" "}
          <button
            onClick={() => dispatch(openRegisterModal())}
            className="text-orange-500 hover:underline"
          >
            Đăng ký
          </button>
        </p>
      </div>
    </div>
  );
};

export default LoginModal;
