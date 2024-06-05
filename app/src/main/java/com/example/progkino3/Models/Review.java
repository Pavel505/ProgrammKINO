package com.example.progkino3.Models;

public class Review {
    public Review(){}
    String review, temaReview,  authorReview;
    String timeReview;
    Boolean scan; // просмотр заявки
    public Review(String review, String temaReview,String authorReview, String timeReview, Boolean scan){
        this.authorReview = authorReview;
        this.review = review;
        this.timeReview = timeReview;
        this.temaReview = temaReview;
        this.scan = scan;
    }
    public Boolean getScan() {
        return scan;
    }

    public void setScan(Boolean scan) {
        this.scan = scan;
    }
    public String getTemaReview() {
        return temaReview;
    }

    public void setTemaReview(String temaReview) {
        this.temaReview = temaReview;
    }
    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
    public String getTimeReview() {
        return timeReview;
    }

    public void setTimeReview(String timeReview) {
        this.timeReview = timeReview;
    }
    public String getAuthorReview() {
        return authorReview;
    }

    public void setAuthorReview(String authorReview) {
        this.authorReview = authorReview;
    }


}
