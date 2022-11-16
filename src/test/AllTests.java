package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ FuncionalidadAdminTest.class, FuncionalidadAdminTest2.class, FuncionalidadOperariosTest.class,
		FuncionalidadOperariosTest2.class, SistemaTest_ColeccionVacia.class, SistemaTest2_ColeccionNoVacia.class })
public class AllTests {

}
