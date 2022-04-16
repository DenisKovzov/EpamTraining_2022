package vsu.by;

import vsu.by.comparators.ComparatorByBladeLength;
import vsu.by.models.Weapon;
import vsu.by.xml.WeaponXmlReader;
import vsu.by.xml.WeaponXmlValidator;
import vsu.by.xml.WeaponXmlWriter;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, XMLStreamException {
        File file = new File("weapons.xml");

        WeaponXmlValidator validator = new WeaponXmlValidator();

        if (validator.validate(file.getPath())) {

            WeaponXmlReader reader = new WeaponXmlReader();

            List<Weapon> weapons = reader.read(file.getName());

            weapons.sort(new ComparatorByBladeLength());

            for (Weapon weapon : weapons) {
                System.out.println(weapon.toString());
            }

            WeaponXmlWriter writer = new WeaponXmlWriter();

            String fileName = "weapon-new.xml";

            writer.write(weapons, fileName);

            System.out.println("Validate " + fileName + ": " + validator.validate(fileName));

        } else {
            System.out.println(validator.getError());
        }


    }
}
