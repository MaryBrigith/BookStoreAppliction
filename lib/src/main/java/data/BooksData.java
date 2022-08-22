package data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BooksData {

    private String userId;
    private List<CollectionOfIsbns> collectionOfIsbns;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setCollectionOfIsbns(List<CollectionOfIsbns> collectionOfIsbns) {
        this.collectionOfIsbns = collectionOfIsbns;
    }

    public class CollectionOfIsbns {
        private String isbn;

        public void setIsbn(String isbn) {
            this.isbn = isbn;
        }
    }
}