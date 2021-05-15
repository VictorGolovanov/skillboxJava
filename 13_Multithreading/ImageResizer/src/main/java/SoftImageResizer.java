import org.imgscalr.Scalr;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class SoftImageResizer implements Runnable
{

    private File file;
    private int newWidth;
    private String dstFolder;
    private long start;

    public SoftImageResizer(File file, int newWidth, String dstFolder, long start){
        this.file = file;
        this.newWidth = newWidth;
        this.dstFolder = dstFolder;
        this.start = start;
    }

    @Override
    public void run() {
        softResizer();
    }

    private void softResizer(){
        try{
            BufferedImage image = Scalr.resize(ImageIO.read(file), newWidth);
            File newFile = new File(dstFolder + "/" + file.getName());
            ImageIO.write(image, "jpg", newFile);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        System.out.println("Finished after start: " + (System.currentTimeMillis() - start) + " ms.");
    }
}
