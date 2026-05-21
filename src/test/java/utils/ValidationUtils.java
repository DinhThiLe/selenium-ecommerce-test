package utils;

import java.util.regex.Pattern;

public class ValidationUtils {

    /**
     * Kiểm tra định dạng email hợp lệ.
     * @param email Địa chỉ email cần kiểm tra
     * @return true nếu hợp lệ, false nếu không hợp lệ
     */
    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.compile(emailRegex).matcher(email).matches();
    }

    /**
     * Tính tổng giá trị giỏ hàng dựa trên mảng giá và mảng số lượng.
     * @param prices Mảng chứa giá các sản phẩm
     * @param qty Mảng chứa số lượng tương ứng
     * @return Tổng tiền (bằng 0 nếu giỏ hàng trống hoặc dữ liệu không hợp lệ)
     */
    public static int calculateTotal(int[] prices, int[] qty) {
        if (prices == null || qty == null || prices.length != qty.length) {
            return 0;
        }
        int total = 0;
        for (int i = 0; i < prices.length; i++) {
            total += prices[i] * qty[i];
        }
        return total;
    }

    /**
     * Kiểm tra mật khẩu có đủ độ mạnh hay không (ít nhất 6 ký tự, có chứa cả chữ và số).
     * @param password Mật khẩu cần kiểm tra
     * @return true nếu mật khẩu mạnh, false nếu không
     */
    public static boolean isStrongPassword(String password) {
        if (password == null || password.length() < 6) {
            return false;
        }
        // Kiểm tra có ít nhất 1 chữ cái và 1 chữ số
        return password.matches(".*[a-zA-Z].*") && password.matches(".*\\d.*");
    }

    /**
     * Kiểm tra mật khẩu hợp lệ (chỉ cần độ dài tối thiểu 6 ký tự).
     * Dùng cho UNIT_07, UNIT_08, UNIT_17
     */
    public static boolean isValidPassword(String password) {
        return password != null && password.length() >= 6;
    }

    /**
     * Kiểm tra từ khóa tìm kiếm hợp lệ.
     * Dùng cho UNIT_10
     */
    public static boolean isValidSearchKeyword(String keyword) {
        return keyword != null && !keyword.trim().isEmpty();
    }

    /**
     * Tính giá trị sau khi được giảm giá.
     * Dùng cho UNIT_11
     */
    public static double calculateDiscount(double price, double discountPercent) {
        if (price < 0 || discountPercent < 0 || discountPercent > 100) {
            return price;
        }
        return price - (price * discountPercent / 100);
    }

    /**
     * Tính tổng số lượng sản phẩm trong giỏ hàng.
     * Dùng cho UNIT_12
     */
    public static int calculateCartQuantity(int[] quantities) {
        if (quantities == null) {
            return 0;
        }
        int total = 0;
        for (int qty : quantities) {
            if (qty > 0) {
                total += qty;
            }
        }
        return total;
    }

    /**
     * Kiểm tra tên sản phẩm hợp lệ.
     * Dùng cho UNIT_13
     */
    public static boolean isValidProductName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    /**
     * Trả về giá trị sản phẩm nếu hợp lệ (lớn hơn 0).
     * Dùng cho UNIT_14
     */
    public static double getProductPrice(double price) {
        return price > 0 ? price : 0.0;
    }

    /**
     * Kiểm tra tính hợp lệ của dữ liệu đăng nhập.
     * Dùng cho UNIT_15
     */
    public static boolean isValidLoginData(String email, String password) {
        return isValidEmail(email) && isValidPassword(password);
    }

    /**
     * Kiểm tra tính hợp lệ của dữ liệu đăng ký.
     * Dùng cho UNIT_16
     */
    public static boolean isValidRegisterData(String name, String email, String password) {
        return name != null && !name.trim().isEmpty() && isValidEmail(email) && isValidPassword(password);
    }

    /**
     * Kiểm tra dữ liệu thanh toán/checkout hợp lệ.
     * Dùng cho UNIT_18
     */
    public static boolean isValidCheckoutData(String name, String address, String city, String zipcode) {
        return name != null && !name.trim().isEmpty() 
                && address != null && !address.trim().isEmpty()
                && city != null && !city.trim().isEmpty()
                && zipcode != null && zipcode.matches("\\d{5,6}");
    }

    /**
     * Kiểm tra giỏ hàng không rỗng.
     * Dùng cho UNIT_19
     */
    public static boolean isCartNotEmpty(int itemCount) {
        return itemCount > 0;
    }

    /**
     * Kiểm tra ảnh sản phẩm có phần mở rộng hợp lệ.
     * Dùng cho UNIT_20
     */
    public static boolean isValidProductImage(String imageUrl) {
        if (imageUrl == null || imageUrl.trim().isEmpty()) {
            return false;
        }
        String lower = imageUrl.toLowerCase();
        return lower.endsWith(".png") || lower.endsWith(".jpg") || lower.endsWith(".jpeg") || lower.endsWith(".gif");
    }

    /**
     * Kiểm tra popup thông báo khớp với thông điệp mong đợi.
     * Dùng cho UNIT_21
     */
    public static boolean isValidPopupMessage(String message, String expected) {
        if (message == null || expected == null) {
            return false;
        }
        return message.trim().equalsIgnoreCase(expected.trim());
    }
}
