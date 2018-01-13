package net.bluecow.voicebus.core.userprefs;

import com.greghaskins.spectrum.Spectrum;
import org.junit.runner.RunWith;

import java.util.List;

import static com.greghaskins.spectrum.Spectrum.describe;
import static com.greghaskins.spectrum.Spectrum.it;
import static net.bluecow.voicebus.core.shared.util.Data.listOf;
import static net.bluecow.voicebus.core.shared.util.Data.setOf;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Spectrum.class)
public class DestinationDaoTest {
    {
        describe("Destination DAO", () -> {
            DestinationDao destinationDao = new DestinationDao();
            it("can save a destination", () -> {
                long time = System.currentTimeMillis();
                Destination destination = new Destination(
                        "test-user-" + time,
                        "test-destination-" + time,
                        null,
                        null,
                        setOf("dest-alias-" + time, "dest-alias-2-" + time),
                        listOf(new Stop("agency", "111", "12345"),
                                new Stop("agency", "222", "22222"),
                                new Stop("agency", "999", "33333")));

                destinationDao.save(destination);

                List<Destination> found = destinationDao.findByUser(destination.getUserId());

                assertThat(found).containsExactly(destination);
            });
        });
    }
}