package phase;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OutboundTest {

	private Outbound outbound; //SUT
	
	@BeforeEach
	void setUp() {
		outbound = new Outbound();
	}
	
	@Test
	void x() {
		assertEquals(Inbound.class, outbound.nextPhase().getClass());  
	}  

}
