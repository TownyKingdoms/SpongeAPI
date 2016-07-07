package org.spongepowered.api.world;

public enum BlockChangeFlag {

    ALL(Flags.NEIGHBOR_MASK | Flags.PHYSICS_MASK),
    NEIGHBOR(Flags.NEIGHBOR_MASK),
    PHYSICS(Flags.PHYSICS_MASK),
    NEIGHBOR_PHYSICS(Flags.NEIGHBOR_MASK | Flags.PHYSICS_MASK),
    NONE()
    ;

    private final boolean updateNeighbors;
    private final boolean performBlockPhysics;
    private final int rawFlag;

    BlockChangeFlag() {
        this.updateNeighbors = false;
        this.performBlockPhysics = false;
        this.rawFlag = 0;
    }

    BlockChangeFlag(int flag) {
        this.updateNeighbors = (flag & Flags.NEIGHBOR_MASK) != 0;
        this.performBlockPhysics = (flag & Flags.PHYSICS_MASK) != 0;
        this.rawFlag = flag;
    }

    public boolean updateNeighbors() {
        return this.updateNeighbors;
    }

    public boolean performBlockPhysics() {
        return this.performBlockPhysics;
    }


    public BlockChangeFlag setUpdateNeighbors(boolean updateNeighbors) {
        if (this.updateNeighbors == updateNeighbors) {
            return this;
        }
        final int maskedFlag = (updateNeighbors ? Flags.NEIGHBOR_MASK : 0)
                               | (this.performBlockPhysics ? Flags.PHYSICS_MASK : 0);
        for (BlockChangeFlag blockChangeFlag : values()) {
            if (blockChangeFlag.rawFlag == maskedFlag) {
                return blockChangeFlag;
            }
        }
        return this;
    }

    public BlockChangeFlag setPerformBlockPhysics(boolean performBlockPhysics) {
        if (this.performBlockPhysics == performBlockPhysics) {
            return this;
        }
        final int maskedFlag = (updateNeighbors ? Flags.NEIGHBOR_MASK : 0)
                               | (performBlockPhysics ? Flags.PHYSICS_MASK : 0);

        for (BlockChangeFlag blockChangeFlag : values()) {
            if (blockChangeFlag.rawFlag == maskedFlag) {
                return blockChangeFlag;
            }
        }
        return this;
    }

    static final class Flags {
        private static final int NEIGHBOR_MASK = 0x010;
        private static final int PHYSICS_MASK = 0x100;
    }



}
