import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "LinkedPurchaseList")
public class LinkedPurchaseList {

    @EmbeddedId
    private LinkedPurchaseKey linkedPurchaseKey;

    public LinkedPurchaseKey getLinkedPurchaseKey() {
        return linkedPurchaseKey;
    }

    public void setLinkedPurchaseKey(LinkedPurchaseKey linkedPurchaseKey) {
        this.linkedPurchaseKey = linkedPurchaseKey;
    }

    public LinkedPurchaseList(){
        //
    }
}
