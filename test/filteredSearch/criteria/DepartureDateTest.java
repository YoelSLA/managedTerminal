package filteredSearch.criteria;

/**
 * Test de unidad para la clase DepartureDate (SUT). Interactua con la clase
 * MaritimeCircuit y Terminal (DOCs);
 * 
 * @author Gabriela Fascetta
 */
class DepartureDateTest {

//	DepartureDate filterA;
//	DepartureDate filterB;
//	LocalDate dateSearchedA = LocalDate.of(2023, 11, 23);
//	LocalDate dateSearchedB = LocalDate.of(2023, 11, 30);
//	ManagedTerminal terminalA = mock(ManagedTerminal.class);
//	ManagedTerminal terminalB = mock(ManagedTerminal.class);
//
//	@BeforeEach
//	void setUp(){
//		filterA = new DepartureDate(dateSearchedA, terminalA);
//	}
//
//	@Test
//	void testClassDepartureDateInitialization() {
//		filterB = new DepartureDate(dateSearchedB, terminalB);
//		assertEquals(dateSearchedA, filterA.getSearchedDate());
//		assertEquals(terminalA, filterA.getManagedTerminal());
//		assertEquals(dateSearchedB, filterB.getSearchedDate());
//		assertEquals(terminalB, filterB.getManagedTerminal());
//	}
//	
//	@Test
//	void testGivenAMaritimeCircuitList_returnAllCircuitsThatHaveTheSameDepartureDateAsTheFilterInstance() {
//		//set up
//		MaritimeCircuit mc1 = mock(MaritimeCircuit.class);
//		MaritimeCircuit mc2 = mock(MaritimeCircuit.class);
//		ShippingLine sc1 = mock(ShippingLine.class);
//		ShippingLine sc2 = mock(ShippingLine.class);
//		
//		
//		List<MaritimeCircuit> listMC = new ArrayList<MaritimeCircuit>();
//		listMC.add(mc1);
//		listMC.add(mc2);
//		
//		List<MaritimeCircuit> listMC1 = new ArrayList<MaritimeCircuit>();
//		listMC1.add(mc1);
//		
//		List<MaritimeCircuit> listMC2 = new ArrayList<MaritimeCircuit>();
//		listMC2.add(mc2);
//		
//		List<MaritimeCircuit> expectedListA = new ArrayList<MaritimeCircuit>();
//		expectedListA.add(mc1);
//		expectedListA.add(mc2);
//		
//		List<ShippingLine> companies = new ArrayList<>();
//		companies.add(sc1);
//		companies.add(sc2);
//		
//		when(terminalA.getShippingCompanies()).thenReturn(companies);
//		when(sc1.getCircuitsWithTripsThatStartOn(dateSearchedA)).thenReturn(listMC1);
//		when(sc2.getCircuitsWithTripsThatStartOn(dateSearchedA)).thenReturn(listMC2);
//		
//		assertEquals(expectedListA, filterA.filterCircuits(listMC));
//	}
}
