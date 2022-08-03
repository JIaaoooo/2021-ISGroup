package NettyWebSocket.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ControlEnum {
    CONTROL_ON(1,"ON"),
    CONTROL_OFF(2,"OFF");

    private final Integer flag ;

    private final String control;

    public static ControlEnum getControlEnumByFlag(int flag){

        for(ControlEnum controlEnum: ControlEnum.values()){
            if(controlEnum.getFlag().equals(flag)){
                return controlEnum;
            }
        }
        return null;
    }
}
