import com.IMSTask.ComplexObjectRequest;
import com.IMSTask.History;
import com.IMSTask.PlayerService;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by yegorm on 04.10.2016.
 */
public class TestPlayerServices  {
    public ComplexObjectRequest request;
    public PlayerService playerService;

    @Test
    public void testVerifyGetBalanceResponseForValidPlayerIfBalanceIsNotEmpty() throws Exception {
        request = new ComplexObjectRequest();
        request.setField1("Ivan");
        request.setField2(12);
        request.setField3((int) System.nanoTime());
        playerService = new PlayerService();

        Assert.assertNotSame(playerService.updatePlayerBalance(request),null);
    }

    @Test
    public void testVerifyUpdatePlayerBalanceForValidPlayer()throws Exception{
        Assert.assertNotSame(playerService.getBalance("Ivan"),null);
    }
}
