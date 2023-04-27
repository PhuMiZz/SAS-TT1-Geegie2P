function getLocaleDateTime(dateTime) {
  return new Date(dateTime);
}

function formateValue(value) {
  if (value < 10) return "0" + value;
  else return value;
}

function formatDateTime(datetime) {
  const months = [
    "Jan",
    "Feb",
    "Mar",
    "Apr",
    "May",
    "Jun",
    "Jul",
    "Aug",
    "Sep",
    "Oct",
    "Nov",
    "Dec",
  ];
  let day = datetime.getDate();
  let month = datetime.getMonth();
  month = months[month];
  let year = datetime.getFullYear();

  return `${formateValue(day)} ${month} ${year}, ${formateValue(
    datetime.getHours()
  )}:${formateValue(datetime.getMinutes())}`;
}

export { getLocaleDateTime, formatDateTime };
