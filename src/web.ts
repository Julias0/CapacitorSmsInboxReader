import { WebPlugin } from '@capacitor/core';
import { SmsInboxReaderPlugin } from './definitions';

export class SmsInboxReaderWeb extends WebPlugin implements SmsInboxReaderPlugin {
  
  getCount(): Promise<{ count: number; }> {
    throw new Error("SMS is not available in Web platform!");
  }
  
  getAllMessages(): Promise<{ sms: { from: string; message: string; }[]; count: number; }> {
    throw new Error("SMS is not available in Web platform!");
  }
  constructor() {
    super({
      name: 'SmsInboxReader',
      platforms: ['web'],
    });
  }
}

const SmsInboxReader = new SmsInboxReaderWeb();

export { SmsInboxReader };

import { registerWebPlugin } from '@capacitor/core';
registerWebPlugin(SmsInboxReader);
