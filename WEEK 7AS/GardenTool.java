public abstract class GardenTool {

    public GardenTool() {
    }

    public abstract String use();
}

class CuttingTool extends GardenTool {

    public CuttingTool() {
        super();
    }

    @Override
    public String use() {

        return superUse()
                + ", blade sharpened first";
    }

    private String superUse() {

        return "Using the tool in the garden";
    }
}

class Pruner extends CuttingTool {

    public Pruner() {
        super();
    }

    @Override
    public String use() {

        String message = super.use();

        return message
                + ", then trimming branches precisely";
    }
}

class GardenToolDemo {

    public static void main(String[] args) {

        CuttingTool c = new CuttingTool();

        Pruner p = new Pruner();

        System.out.println(c.use());
        System.out.println(p.use());
    }
}