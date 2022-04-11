package vsu.by.xml;

import vsu.by.models.*;
import vsu.by.models.visualWeapon.*;
import vsu.by.models.visualWeapon.BladeMaterial;
import vsu.by.models.visualWeapon.HandleMaterial;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class WeaponXmlReader {
    public List<Weapon> read(String fileName) throws FileNotFoundException {
        XMLStreamReader reader = null;

        try {
            List<Weapon> weapons = new ArrayList<>();

            Weapon weapon = null;

            XMLInputFactory factory = XMLInputFactory.newFactory();

            reader = factory.createXMLStreamReader(new FileInputStream(fileName));

            while (reader.hasNext()) {
                int type = reader.next();

                switch (type) {
                    case XMLStreamConstants.START_ELEMENT: {
                        String tagName = reader.getLocalName();

                        if ("weapon".equals(tagName)) {
                            weapon = new Weapon();
                            weapon.setId(reader.getAttributeValue(null, "id"));
                        } else if ("Type".equals(tagName)) {
                            weapon.setWeaponType(WeaponType.valueOf(reader.getElementText()));
                        } else if ("Handy".equals(tagName)) {
                            weapon.setHandleType(HandleType.valueOf(reader.getElementText()));
                        } else if ("Origin".equals(tagName)) {
                            weapon.setOrigin(reader.getElementText());
                        } else if ("bladeLength".equals(tagName)) {
                            weapon.addVisual(new BladeLength(reader.getElementText()));
                        } else if ("bladeWidth".equals(tagName)) {
                            weapon.addVisual(new BladeWidth(reader.getElementText()));
                        } else if ("bladeMaterial".equals(tagName)) {
                            weapon.addVisual(new BladeMaterial(reader.getElementText()));
                        } else if ("handleMaterial".equals(tagName)) {
                            weapon.addVisual(new HandleMaterial(reader.getElementText()));
                        } else if ("bloodstream".equals(tagName)) {
                            weapon.addVisual(new Bloodstream(reader.getElementText()));
                        } else if ("Value".equals(tagName)) {
                            weapon.setValue(Boolean.valueOf(reader.getElementText()));
                        }
                        break;
                    }
                    case XMLStreamConstants.END_ELEMENT: {
                        String tagName = reader.getLocalName();
                        if ("weapon".equals(tagName)) {
                            weapons.add(weapon);
                        }
                        break;
                    }
                }
            }
            return weapons;
        } catch (XMLStreamException e) {
            return null;
        } finally {
            try {
                reader.close();
            } catch (Exception e) {
            }
        }

    }
}
