/**
 */
package duly;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see duly.DulyPackage
 * @generated
 */
public interface DulyFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DulyFactory eINSTANCE = duly.impl.DulyFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Demo</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Demo</em>'.
	 * @generated
	 */
	Demo createDemo();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	DulyPackage getDulyPackage();

} //DulyFactory
