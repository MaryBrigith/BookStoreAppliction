package data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@JsonIgnoreProperties(ignoreUnknown = true)

public class BooksRespons {


	public BooksRespons() {
		// TODO Auto-generated constructor stub
	}

	private List<Books> books;

    public List<Books> getBooks() {
        return books;
    }

    public class Books {
        private String author;
        private String description;
        private String isbn;
        private Number pages;
        private String publish_date;
        private String publisher;
        private String subTitle;
        private String title;
        private String website;

        public String getIsbn() {
            return isbn;
        }

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Number getPages() {
			return pages;
		}

		public void setPages(Number pages) {
			this.pages = pages;
		}

		public String getPublish_date() {
			return publish_date;
		}

		public void setPublish_date(String publish_date) {
			this.publish_date = publish_date;
		}

		public String getPublisher() {
			return publisher;
		}

		public void setPublisher(String publisher) {
			this.publisher = publisher;
		}

		public String getSubTitle() {
			return subTitle;
		}

		public void setSubTitle(String subTitle) {
			this.subTitle = subTitle;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getWebsite() {
			return website;
		}

		public void setWebsite(String website) {
			this.website = website;
		}
    }
}