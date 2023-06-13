package com.MyApplication.modules.product;

import com.MyApplication.interfaces.IService;
import com.MyApplication.modules.product.entity.Product;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

public class ProductService implements IService<Product> {
    @Override
    public void create(Product product) {
        try {
            CallableStatement callableStatement =
                    connection.prepareCall("{CALL create_product(?,?,?,?,?,?,?)}");
            callableStatement.setInt(1, product.getId_user_created());
            callableStatement.setInt(2, product.getId_category());
            callableStatement.setString(3, product.getName());
            callableStatement.setString(4, product.getDescription());
            callableStatement.setInt(5, product.getQuantity());
            callableStatement.setString(6, product.getImg());
            callableStatement.setInt(7, product.getPrice());
            callableStatement.execute();
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    @Override
    public ArrayList<Product> findAll() {
        return null;
    }

    @Override
    public int update(Product product) {
        return 0;
    }

    @Override
    public int remove(Product product) {
        return 0;
    }

    @Override
    public Product findById(int id) {
        try {
            CallableStatement callableStatement =
                    connection.prepareCall("{call findProductById(?,?,?,?,?,?,?,?)}");
            callableStatement.setInt(1, id);
            callableStatement.registerOutParameter(2, Types.BIGINT);
            callableStatement.registerOutParameter(3, Types.BIGINT);
            callableStatement.registerOutParameter(4, Types.VARCHAR);
            callableStatement.registerOutParameter(5, Types.VARCHAR);
            callableStatement.registerOutParameter(6, Types.BIGINT);
            callableStatement.registerOutParameter(7, Types.VARCHAR);
            callableStatement.registerOutParameter(8, Types.BIGINT);
            callableStatement.execute();
            return new Product(
                    id,
                    callableStatement.getInt(2),
                    callableStatement.getInt(3),
                    callableStatement.getNString(4),
                    callableStatement.getNString(5),
                    callableStatement.getInt(6),
                    callableStatement.getInt(8),
                    callableStatement.getNString(7)
            );
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return null;
    }
}
