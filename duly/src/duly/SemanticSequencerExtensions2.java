package duly;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.EObjectDescription;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IReferenceDescription;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.resource.impl.AbstractResourceDescription;
import org.eclipse.xtext.resource.impl.ResourceDescriptionsData;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xtext.generator.serializer.SemanticSequencerExtensions;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@SuppressWarnings("restriction")
public class SemanticSequencerExtensions2 extends SemanticSequencerExtensions {

	@Override
	protected ResourceSet cloneResourceSet(ResourceSet rs) {
		// TODO Auto-generated method stub
		ResourceSet cloneResourceSet = super.cloneResourceSet(rs);
		installIndex(rs, cloneResourceSet);
		if (cloneResourceSet instanceof XtextResourceSet && rs instanceof XtextResourceSet) {
			Map<URI, URI> normalizationMap = ((XtextResourceSet) rs).getNormalizationMap();
			// for (String e : normalizationMap.entrySet()) {
			// ((XtextResourceSet)cloneResourceSet).c
			// }
			// getNormalizationMap().putAll(normalizationMap);
		}
		return cloneResourceSet;
	}
	
	  public Grammar getSuperGrammar(final Grammar grammar) {
		    boolean _isEmpty = grammar.getUsedGrammars().isEmpty();
		    if (_isEmpty) {
		      return null;
		    }
		    Adapter _existingAdapter = EcoreUtil.getExistingAdapter(grammar, SemanticSequencerExtensions.SuperGrammar.class);
		    SemanticSequencerExtensions.SuperGrammar sg = ((SemanticSequencerExtensions.SuperGrammar) _existingAdapter);
		    if ((sg != null)) {
		      return sg.getGrammar();
		    }
		    final URI uri = IterableExtensions.<Grammar>head(grammar.getUsedGrammars()).eResource().getURI();
		    final Resource resource = this.cloneResourceSet(grammar.eResource().getResourceSet()).getResource(uri, true);
		   XtextResourceSet orig = (XtextResourceSet)grammar.eResource().getResourceSet();
		   System.err.println(orig.getNormalizationMap()); 
		   for (Resource rx : grammar.eResource().getResourceSet().getResources()) {
			    	if (rx != grammar.eResource()) {
			    		if (rx.getURI().isFile() || rx.getURI().isArchive() || rx.getURI().isPlatform())
			    		resource.getResourceSet().getResource(orig.getNormalizationMap().get(rx.getURI()), true);
			    	}
		    }
		    EObject _head = IterableExtensions.<EObject>head(resource.getContents());
		    final Grammar result = ((Grammar) _head);
		    EList<Adapter> _eAdapters = grammar.eAdapters();
		    SemanticSequencerExtensions.SuperGrammar _superGrammar = new SemanticSequencerExtensions.SuperGrammar(result);
		    _eAdapters.add(_superGrammar);
		    return result;
		  }

	private void installIndex(ResourceSet original, ResourceSet clone) {
		ResourceDescriptionsData index = new ResourceDescriptionsData(Collections.emptyList());
		List<Resource> resources = Lists.newArrayList(original.getResources());
		for (Resource resource : resources) {
			index(resource, resource.getURI(), index);
		}
		ResourceDescriptionsData.ResourceSetAdapter.installResourceDescriptionsData(clone, index);
	}

	private void index(Resource resource, URI uri, ResourceDescriptionsData index) {
		if ("ecore".equals(uri.fileExtension())) {
			IResourceServiceProvider serviceProvider = IResourceServiceProvider.Registry.INSTANCE.getResourceServiceProvider(uri);
			if (serviceProvider != null) {
				IResourceDescription resourceDescription = serviceProvider.getResourceDescriptionManager().getResourceDescription(resource);
				if (resourceDescription != null) {
					index.addDescription(uri, resourceDescription);
				}
			}
		}
	}
	
	static class CopiedResourceDescription extends AbstractResourceDescription {

		private URI uri;

		private ImmutableList<IEObjectDescription> exported;

		public CopiedResourceDescription(IResourceDescription original) {
			this.uri = original.getURI();
			this.exported = ImmutableList.copyOf(Iterables.transform(original.getExportedObjects(),
					new Function<IEObjectDescription, IEObjectDescription>() {
						@Override
						public IEObjectDescription apply(IEObjectDescription from) {
							if (from.getEObjectOrProxy().eIsProxy()) {
								return from;
							}
							InternalEObject result = (InternalEObject) EcoreUtil.create(from.getEClass());
							result.eSetProxyURI(from.getEObjectURI());
							Map<String, String> userData = null;
							for (final String key : from.getUserDataKeys()) {
								if (userData == null) {
									userData = Maps.newHashMapWithExpectedSize(2);
								}
								userData.put(key, from.getUserData(key));
							}
							return EObjectDescription.create(from.getName(), result, userData);
						}
					}));
		}

		@Override
		protected List<IEObjectDescription> computeExportedObjects() {
			return exported;
		}

		@Override
		public Iterable<QualifiedName> getImportedNames() {
			throw new RuntimeException();
		}

		@Override
		public Iterable<IReferenceDescription> getReferenceDescriptions() {
			throw new RuntimeException();
		}

		@Override
		public URI getURI() {
			return uri;
		}
	}

}
