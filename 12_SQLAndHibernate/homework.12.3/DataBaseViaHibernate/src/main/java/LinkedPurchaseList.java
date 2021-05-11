import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "LinkedPurchaseList")
public class LinkedPurchaseList {

    @EmbeddedId
    private LinkedPurchaseKey linkedPurchaseKey;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    @Column(name = "price")
    private Integer price;

    ////
    @Column(name = "student_name")
    private String studentName;

    @Column(name = "course_name")
    private String courseName;



    public LinkedPurchaseKey getLinkedPurchaseKey() {
        return linkedPurchaseKey;
    }

    public void setLinkedPurchaseKey(LinkedPurchaseKey linkedPurchaseKey) {
        this.linkedPurchaseKey = linkedPurchaseKey;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public LinkedPurchaseList(){
        //
    }
}
