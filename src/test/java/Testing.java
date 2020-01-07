import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import base.BlankDisc;
import base.ConfigurationClass;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ConfigurationClass.class)
public class Testing {

    @Autowired
    BlankDisc blankDisc;

    @Test
    public void csShouldntBeNull(){
        Assertions.assertEquals(blankDisc.test(), "It's Working");

    }


}
