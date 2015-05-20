package se.sundsvall.openemap.saml;

import org.opensaml.saml2.core.Assertion;
import org.opensaml.saml2.core.Attribute;
import org.opensaml.xml.schema.impl.XSStringImpl;

import se.unlogic.hierarchy.core.beans.User;
import se.unlogic.hierarchy.foregroundmodules.userproviders.SimpleUser;
import se.unlogic.openhierarchy.foregroundmodules.samluseradapter.SimpleSAMLUserAdapter;
import se.unlogic.standardutils.time.TimeUtils;

public class UserAdapter extends SimpleSAMLUserAdapter {

	@Override
	public User getUser(Assertion assertion) {
		for (Attribute attribute : assertion.getAttributeStatements().get(0).getAttributes()) {

			if(attribute.getName().equals(assertionAttribute)){
				
				String attributeValue = ((XSStringImpl) attribute.getAttributeValues().get(0)).getValue();
				
				if(attributeValue == null){
					
					return null;
				}
				
				SimpleUser user = new SimpleUser();
				user.setEnabled(true);
				user.setUsername(attributeValue);
				user.setFirstname(attributeValue);
				user.setLastname(attributeValue);
				user.setUserID(-1);
				user.setAdded(TimeUtils.getCurrentTimestamp());
				user.setLastLogin(TimeUtils.getCurrentTimestamp());
				return user;
			}
			
		}
		
		return null;
	}

}
