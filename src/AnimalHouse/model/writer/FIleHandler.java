package AnimalHouse.model.writer;

import AnimalHouse.model.house.House;

import java.io.*;

public class FIleHandler implements Writable {
    private String filepath;

    @Override
    public boolean write(Serializable serializable, String filepath) {

        boolean flag = false;

        File file = new File(filepath);
        ObjectOutputStream oos = null;
        try (FileOutputStream fos = new FileOutputStream(file)) {
            if (fos != null) {
                oos = new ObjectOutputStream(fos);
                oos.writeObject(serializable);
                flag = true;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public Object read(String filepath) throws IOException {
        File file = new File(filepath);
        ObjectInputStream ois = null;
        try (FileInputStream fis = new FileInputStream(file)) {
            if (fis != null) {
                ois = new ObjectInputStream(fis);
                House readAnimals = (House) ois.readObject();
                return readAnimals;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        throw new InvalidObjectException("Произошла ошибка!");
    }

}
