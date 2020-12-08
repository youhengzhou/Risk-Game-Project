import javax.xml.bind.annotation.*;
import java.util.Arrays;
@XmlRootElement(name = "ButtonInfo")
@XmlAccessorType(XmlAccessType.FIELD)
public class ButtonInfo {
    String actionCommand;
    @XmlElementWrapper(name = "location")
            @XmlElement(name = "pix")
    int[] location;

    public ButtonInfo()
    {
        location = new int[4];
        actionCommand = "";
    }
    public ButtonInfo(String ac, int x,int y,int width, int height)
    {
        location = new int[4];
        actionCommand = ac;
        location[0] = x;
        location[1] = y;
        location[2] = width;
        location[3] = height;
    }
    public void setActionCommand(String ac)
    {
        actionCommand = ac;
    }

    public void setLocation(int x, int y, int width, int height)
    {
        location[0] = x;
        location[1] = y;
        location[2] = width;
        location[3] = height;
    }

    public int[] getLocation() {
        return location;
    }

    public String getActionCommand() {
        return actionCommand;
    }

    @Override
    public String toString() {
        return "ButtonInfo{" +
                "actionCommand='" + actionCommand + '\'' +
                ", location=" + Arrays.toString(location) +
                '}';
    }
}
