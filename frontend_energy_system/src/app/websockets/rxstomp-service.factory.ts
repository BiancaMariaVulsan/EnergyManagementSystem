import { WebsocketRxStompConfig } from "./rxstomp.config";
import { RxStompService } from "./rxstomp.service";

export function rxStompServiceFactory() {
  const rxStomp = new RxStompService();
  rxStomp.configure(WebsocketRxStompConfig);
  rxStomp.activate();
  return rxStomp;
}
