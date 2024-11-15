import java.util.LinkedList;
import java.util.Queue;

class Pet {
    private String type;

    public Pet (String type) {
        this.type = type;
    }

    public String getPetType() {
        return this.type;
    }
}

class Dog extends Pet {
    public Dog() {
        super("dog");
    }
}

class Cat extends Pet {
    public Cat() {
        super("cat");
    }
}

class PetEnterQueue {
    private Pet pet;
    private long count;

    public PetEnterQueue(Pet pet, long count) {
        this.pet = pet;
        this.count = count;
    }

    public Pet getPet() {
        return this.pet;
    }

    public long getCount() {
        return this.count;
    }

    public String getEnterPetType() {
        return this.pet.getPetType();
    }
}

public class PetQueue {
    private Queue<PetEnterQueue> dogQ;
    private Queue<PetEnterQueue> catQ;
    long count;
    public PetQueue() {
        this.dogQ = new LinkedList<PetEnterQueue>();
        this.catQ = new LinkedList<PetEnterQueue>();
        count = 0;
    }

//    用户可以调用add方法将cat类或dog类的实例放入队列中；
    public void pollAll(Pet pet) {
        PetEnterQueue newpet = new PetEnterQueue(pet, count++);
        if(pet.getPetType().equals("cat")) {
            catQ.add(newpet);
        } else {
            dogQ.add(newpet);
        }
    }
//    用户可以调用pollAll方法，将队列中所有的实例按照进队列的先后顺序依次弹出；
    public Pet pollAll() {
        Pet pet = null;
        if(! dogQ.isEmpty() && ! catQ.isEmpty()) {
            if(dogQ.peek().getCount() > catQ.peek().getCount()) {
                pet = dogQ.poll().getPet();
            } else {
                pet = catQ.poll().getPet();
            }
        } else if(! dogQ.isEmpty()) {
            pet = dogQ.poll().getPet();
        } else if(! catQ.isEmpty()){
            pet = catQ.poll().getPet();
        } else {
            throw new RuntimeException("PetQueue is empty");
        }
        return pet;
    }

//    用户可以调用pollDog方法，将队列中dog类的实例按照进队列的先后顺序依次弹出；
    public Dog pollDog() {
        Dog dog = null;
        if (dogQ.isEmpty()) {
            throw new RuntimeException("PetQueue have not Dog");
        }
        dog = (Dog) dogQ.poll().getPet();
        return dog;
    }

//    用户可以调用pollCat方法，将队列中cat类的实例按照进队列的先后顺序依次弹出；
    public Cat pollCat() {
        Cat cat = null;
        if (catQ.isEmpty()) {
            throw new RuntimeException("PetQueue have not Cat");
        }
        cat = (Cat) catQ.poll().getPet();
        return cat;
    }

//    用户可以调用isEmpty方法，检查队列中是否还有dog或cat的实例
    public boolean isEmpty() {
        if (catQ.isEmpty() && dogQ.isEmpty()) {
            return true;
        }
        return false;
    }

//    用户可以调用isDogEmpty方法，检查队列中是否还有dog类的实例
    public boolean isDogEmpty() {
        return dogQ.isEmpty();
    }

//    用户可以调用isCatEmpty方法，检查队列中是否有cat类的实例
    public boolean isCatEmpty() {
        return catQ.isEmpty();
    }
}