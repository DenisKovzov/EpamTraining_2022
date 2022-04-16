package vsu.by.xml;

import vsu.by.controller.weapon.set.*;
import vsu.by.models.*;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeaponXmlReader {

    private String WEAPON_NAME = "weapon";

    public List<Weapon> read(String fileName) throws FileNotFoundException {
        XMLStreamReader reader = null;
        Map<String, FieldSetter> mapAction = new HashMap<>();

        initializeMapAction(mapAction);

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

                        if (WEAPON_NAME.equals(tagName)) {
                            weapon = new Weapon();
                            weapon.setId(reader.getAttributeValue(null, "id"));
                        } else {
                            FieldSetter field = mapAction.get(tagName);
                            if (field != null) {
                                field.set(weapon, reader.getElementText());
                            }
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

    private void initializeMapAction(Map<String, FieldSetter> map) {
        map.put("Type", new SetWeaponType());
        map.put("Handy", new SetHandleType());
        map.put("Origin", new SetOrigin());
        map.put("bladeLength", new SetBladeLength());
        map.put("bladeWidth", new SetBladeWidth());
        map.put("bladeMaterial", new SetBladeMaterial());
        map.put("handleMaterial", new SetHandleMaterial());
        map.put("bloodstream", new SetBloodStream());
        map.put("Value", new SetValue());

    }
}