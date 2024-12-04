import { KafkaMessage } from "kafkajs";
import logger from "../config/logger";
import { IBookingData, IUser } from "./types";
import { getBookingDetails } from "./booking.service";
import { getUserDetails } from "./auth.service";
import { IMailOptions, sendMail } from "../config";
import { formatShortDate } from "../utils";

export async function sendBookingMailService(message: KafkaMessage) {
  logger.info("Handling booking mail event");
  const payload = JSON.parse(message.value?.toString()!);

  /**
    .ownerId(rideCreatedEvent.ownerId)
                        .destination(rideCreatedEvent.destination)
                        .fare(rideCreatedEvent.fare)
                        .source(rideCreatedEvent.source)
                        .vehicleNumber(rideCreatedEvent.vehicleNumber)
  */

  logger.info("Got the payload: {}", payload);
  console.log(payload);

  const userData: IUser = await getUserDetails(payload.ownerId);

  const bookingUpdateMessage = `
    <div>
      <h1>Ride Update</h1>
      <p>Dear Valued Customer,</p>
      <p>We received the following information regarding your booking:</p>
      <h3>Ride Summary:</h3>
      <ul>
        <li><strong>Source:</strong>${payload.source}</li>
        <li><strong>Destination:</strong> ${payload.destination}</li>
        <li><strong>Fare:</strong> ${payload.fare}</li>
        <li><strong>Vehicle:</strong> ${payload.vehicleNumber}</li>
        <li><strong>Owner ID:</strong> ${payload.ownerId}</li>
        <li><strong>Rider Status:</strong> ${payload.riderStatus}</li>
        <li><strong>Service:</strong> ${payload.service}</li>
      </ul>
      <p><em>Message:</em> Got the payload: {}</p>
      <p>If you have any questions or require further assistance, please don't hesitate to contact us.</p>
      <p>Best regards,</p>
      <p>The Mail-Service Team</p>
    </div>
  `;

  const mailData: IMailOptions = {
    from: {
      name: "CarPooling",
      address: process.env.MAIL_USER!,
    },
    to: userData.email,
    subject: `Ride Booked #${payload.vehicleNumber}`,
    html: bookingUpdateMessage,
  };

  logger.info(`Sending a mail`);
  await sendMail(mailData);
  logger.info(`Message sent successfully!`);
}
