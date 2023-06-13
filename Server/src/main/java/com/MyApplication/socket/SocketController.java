package com.MyApplication.socket;

import com.MyApplication.modules.auth.AuthService;
import com.MyApplication.modules.auth.dto.SignInDto;
import com.MyApplication.modules.category.CategoryService;
import com.MyApplication.modules.category.entity.Category;
import com.MyApplication.modules.product.ProductService;
import com.MyApplication.modules.product.entity.Product;
import com.MyApplication.modules.user.UserService;
import com.MyApplication.modules.user.dto.CreateAccountDto;
import com.MyApplication.modules.user.entity.User;
import com.MyApplication.modules.user_details.UserDetailsService;
import com.MyApplication.modules.user_details.entity.UserDetails;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class SocketController {
    private final AuthService authService;
    private final UserService userService;
    private final CategoryService categoryService;
    private final UserDetailsService userDetailsService;
    private final ProductService productService;
    private String address;

    public SocketController() {
        this.authService = new AuthService();
        this.userService = new UserService();
        this.userDetailsService = new UserDetailsService();
        this.categoryService = new CategoryService();
        this.productService = new ProductService();
    }

    private JSONObject response(String action, String message, Object data) {
        JSONObject response = new JSONObject();
        response.put("action", action);
        Map<String, Object> dt = new LinkedHashMap<>();
        dt.put("message", message);
        dt.put("data", data);
        response.put("data", new JSONObject(dt));
        return response;
    }

    public JSONObject handleAction(String action, JSONObject data) {
        switch (action) {
            case "POST_SIGN_IN" -> {
                return this.SIGN_IN(data);
            }
            case "POST_REGISTER" -> {
                return this.REGISTER(data);
            }
            case "POST_CREATE_CATEGORY" -> {
                return this.CREATE_CATEGORY(data);
            }
            case "GET_CATEGORY" -> {
                return this.GET_CATEGORY();
            }
            case "POST_PRODUCT" -> {
                return this.CREATE_PRODUCT(data);
            }
            case "GET_PRODUCT" -> {
                return this.GET_PRODUCT();
            }
        }
        return null;
    }

    //FOR AUTHENTICATION
    @NotNull
    private JSONObject SIGN_IN(JSONObject data) {
        String username = (String) data.get("username");
        String password = (String) data.get("password");
        SignInDto signInDto = new SignInDto(username, password);
        User verify = this.authService.signIn(signInDto);
        if (verify == null) {
            return this.response(
                    "RES_SIGN_IN",
                    "Lỗi đăng nhập ! Tài khoản hoặc mật khẩu không chính xác. Vui lòng kiểm tra lại",
                    null);
        }
        UserDetails userDetailsEntity = this.userDetailsService.findById(verify.getId_user_details());
        Map<String, Object> userDetails = new LinkedHashMap<>();
        userDetails.put("id", userDetailsEntity.getId());
        userDetails.put("firstName", userDetailsEntity.getFirstName());
        userDetails.put("lastName", userDetailsEntity.getLastName());
        userDetails.put("address", userDetailsEntity.getAddress());
        userDetails.put("phoneNumber", userDetailsEntity.getPhoneNumber());
        userDetails.put("email", userDetailsEntity.getEmail());
        return this.response("RES_SIGN_IN",
                "Đăng nhập thành công !", new JSONObject(userDetails));
    }

    @NotNull
    private JSONObject REGISTER(JSONObject data) {
        String username = data.getString("username");
        String password = data.getString("password");
        String firstName = data.getString("firstName");
        String lastName = data.getString("lastName");
        String email = data.getString("email");
        String phoneNumber = data.getString("phoneNumber");
        String address = data.getString("address");
        CreateAccountDto createAccountDto = new CreateAccountDto(
                username,
                password,
                firstName,
                lastName,
                email,
                phoneNumber,
                address
        );
        this.userService.create_account(createAccountDto);
        return this.response("RES_REGISTER", "Tạo tài khoản thành công", null);
    }

    //FOR CATEGORY
    @NotNull
    private JSONObject CREATE_CATEGORY(JSONObject data) {
        String name = data.getString("name");
        this.categoryService.create(new Category(name));
        return response("RES_CREATE_CATEGORY", "Tạo thành công", null);
    }

    @NotNull
    private JSONObject GET_CATEGORY() {
        ArrayList<Category> categories = this.categoryService.findAll();
        JSONArray arrJson = new JSONArray();
        categories.forEach(category -> {
            Map<String, Object> item = new LinkedHashMap<>();
            item.put("id", category.getId());
            item.put("name", category.getName());
            arrJson.put(new JSONObject(item));
        });
        return this.response("RES_GET_CATEGORY", "Lấy dữ liệu thành công", arrJson);
    }

    //FOR PRODUCT
    @NotNull
    private JSONObject CREATE_PRODUCT(JSONObject data) {
        int id_user_created = data.getInt("id_user_created");
        int id_category = data.getInt("id_category");
        String name = data.getString("name");
        String description = data.getString("description");
        int quantity = data.getInt("quantity");
        int price = data.getInt("price");
        String img = data.getString("img");
        this.productService.create(new Product(
                id_user_created,
                id_category,
                name,
                description,
                quantity,
                price,
                img
        ));
        return response("RES_CREATE_CATEGORY", "Tạo thành công", null);
    }

    @NotNull
    private JSONObject GET_PRODUCT() {
        ArrayList<Product> products = this.productService.findAll();
        JSONArray arrJson = new JSONArray();
        products.forEach(product -> {
            Category category = this.categoryService.findById(product.getId_category());
            Map<String, Object> item = new LinkedHashMap<>();
            item.put("category_name", category.getName());
            item.put("name", product.getName());
            item.put("description", product.getDescription());
            item.put("price", product.getPrice());
            item.put("quantity", product.getQuantity());
            item.put("img", product.getImg());
            arrJson.put(new JSONObject(item));
        });
        return this.response("RES_GET_PRODUCT", "Lấy dữ liệu thành công", arrJson);
    }
}
