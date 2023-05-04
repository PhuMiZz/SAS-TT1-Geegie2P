function getLocaleDateTime(dateTime) {
  const date = new Date(dateTime);
  const option = {
    day: "numeric",
    month: "short",
    year: "numeric",
    hour: "2-digit",
    minute: "2-digit",
  };
  const userLocale = navigator.language || "en-GB";
  return new Intl.DateTimeFormat(userLocale, option).format(date);
}

function getISODateTime(date, time) {
  const format = `${date}T${time}`;
  const newDate = new Date(format);

  return newDate.toISOString();
}
function extractDateAndTime (dateTime) {
  if (!dateTime) return ['', ''];

  const isoString = new Date(dateTime).toISOString();
  const date = isoString.split('T')[0];
  const time = isoString.split('T')[1].substring(0, 5);

  return [date, time];
}

export { getLocaleDateTime, getISODateTime,extractDateAndTime };
