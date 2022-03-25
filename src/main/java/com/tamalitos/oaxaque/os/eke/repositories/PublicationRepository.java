package com.tamalitos.oaxaque.os.eke.repositories;

import com.tamalitos.oaxaque.os.eke.db.MysqlConnection;
import com.tamalitos.oaxaque.os.eke.exceptions.PublicationCreateException;
import com.tamalitos.oaxaque.os.eke.exceptions.PublicationNotFoundException;
import com.tamalitos.oaxaque.os.eke.models.Publication;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class PublicationRepository {
    final private MysqlConnection mysqlConnection = new MysqlConnection();

    public List<Publication> getPublications() throws SQLException {
        String sql = String.format("select * from publication");
        ResultSet s =this.mysqlConnection.executeQuery(sql);
        List<Publication> publications = new ArrayList<Publication>();
        while (s.next()) {
            publications.add(
                new Publication(
                    s.getInt("id"),
                    s.getString("title"),
                    s.getString("description"),
                    s.getDate("publication_date")));
        }
        return publications;
    }

    public Publication getPublicationById(int id) throws SQLException, PublicationNotFoundException {
        String sql = String.format("select * from publication where id = %d", id);
        ResultSet s =this.mysqlConnection.executeQuery(sql);
        Publication publication;
        if (s.next()) {
            publication = new Publication(
                    s.getInt("id"),
                    s.getString("title"),
                    s.getString("description"),
                    s.getDate("publication_date")
            );
            return publication;
        }
        throw new PublicationNotFoundException();
    }

    public void createPublication(String title, String description, Date publication_date) throws PublicationCreateException {
        String sql = String.format("insert into publication (title, description, publication_date)" +
                " values ('%1$s', '%2$s', '%3$s')", title, description, publication_date);
        int s = this.mysqlConnection.executeUpdate(sql);
        this.mysqlConnection.disconnect();
        if(s == 0)
            throw new PublicationCreateException();
    }

    public void updatePublication(int id, String title, String description, Date publication_date) throws PublicationNotFoundException {
        String sql = String.format("update publication set " +
                "title = '%1$s', description = '%2$s', publication_date = '%3$s' " +
                "where id = %4$d", title, description, publication_date, id);
        int s = this.mysqlConnection.executeUpdate(sql);
        this.mysqlConnection.disconnect();
        if(s == 0)
            throw new PublicationNotFoundException();
    }

    public void deletePublication(int id) throws PublicationNotFoundException {
        String sql = String.format("delete from publication where id = %d", id);
        int s = this.mysqlConnection.executeUpdate(sql);
        this.mysqlConnection.disconnect();
        if(s == 0)
            throw new PublicationNotFoundException();
    }
}
