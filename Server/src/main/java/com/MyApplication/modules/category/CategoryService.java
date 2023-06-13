package com.MyApplication.modules.category;

import com.MyApplication.interfaces.IService;
import com.MyApplication.modules.category.entity.Category;

import java.sql.*;
import java.util.ArrayList;

public class CategoryService implements IService<Category> {
    @Override
    public void create(Category category) {
        try {
            String query = "INSERT INTO category(name) VALUES (?)";
            CallableStatement callableStatement = connection.prepareCall(query);
            callableStatement.setString(1, category.getName());
            callableStatement.execute();
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    @Override
    public ArrayList<Category> findAll() {
        ArrayList<Category> categories = new ArrayList<>();
        try {
            String query = "select * from category";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getNString("name");
                categories.add(new Category(id, name));
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return categories;
    }

    @Override
    public int update(Category category) {
        return 0;
    }

    @Override
    public int remove(Category category) {
        return 0;
    }

    @Override
    public Category findById(int id) {
        try {
            CallableStatement callableStatement = connection.prepareCall("{Call findCategoryById(?,?)}");
            callableStatement.setInt(1,id);
            callableStatement.registerOutParameter(2, Types.VARCHAR);
            callableStatement.execute();
            return new Category(id, callableStatement.getNString(2));
        } catch (SQLException e){
            e.getStackTrace();
        }
        return null;
    }
}
