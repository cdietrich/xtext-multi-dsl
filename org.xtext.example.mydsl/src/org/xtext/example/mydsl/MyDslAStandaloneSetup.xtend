/*
 * generated by Xtext 2.12.0
 */
package org.xtext.example.mydsl


/**
 * Initialization support for running Xtext languages without Equinox extension registry.
 */
class MyDslAStandaloneSetup extends MyDslAStandaloneSetupGenerated {

	def static void doSetup() {
		new MyDslAStandaloneSetup().createInjectorAndDoEMFRegistration()
	}
}
