import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import org.imgscalr.Scalr;

public class ImageResizer implements Runnable
{
    private File[] files;
    private int newWidth;
    private String dstFolder;
    private long start;
    private int FLAG;

    public ImageResizer(File[] files, int newWidth, String dstFolder, long start, int FLAG){
        this.files = files;
        this.newWidth = newWidth;
        this.dstFolder = dstFolder;
        this.start = start;
        this.FLAG = FLAG;
    }

    @Override
    public void run() {
        if(FLAG == 0){
            hardResizer();
        }
        if(FLAG == 1){
            softResizer();
        }
        else{
            System.out.println("Wrong FLAG: 0 - call hardResizer(), 1 - call softResizer()");
        }
    }

    private void hardResizer(){
        try {
            for (File file : files) {
                BufferedImage image = ImageIO.read(file);
                if (image == null) {
                    continue;
                }

                int newHeight = (int) Math.round(
                        image.getHeight() / (image.getWidth() / (double) newWidth)
                );
                BufferedImage newImage = new BufferedImage(
                        newWidth, newHeight, BufferedImage.TYPE_INT_RGB
                );

                int widthStep = image.getWidth() / newWidth;
                int heightStep = image.getHeight() / newHeight;

                for (int x = 0; x < newWidth; x++) {
                    for (int y = 0; y < newHeight; y++) {
                        int rgb = image.getRGB(x * widthStep, y * heightStep);
                        newImage.setRGB(x, y, rgb);
                    }
                }

                File newFile = new File(dstFolder + "/" + file.getName());
                ImageIO.write(newImage, "jpg", newFile);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("Finished after start: " + (System.currentTimeMillis() - start) + " ms.");
    }

    private void softResizer(){
        try{
            for(File file : files){
                BufferedImage image = Scalr.resize(ImageIO.read(file), newWidth);
                File newFile = new File(dstFolder + "/" + file.getName());
                ImageIO.write(image, "jpg", newFile);
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        System.out.println("Finished after start: " + (System.currentTimeMillis() - start) + " ms.");
    }
}
