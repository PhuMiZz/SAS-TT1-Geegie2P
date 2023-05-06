function getLocaleDateTime(dateTime) {
  const date = new Date(dateTime);
  const option = {
    day: 'numeric',
    month: 'short',
    year: 'numeric',
    hour: '2-digit',
    minute: '2-digit',
  };
  const userLocale = navigator.language || 'en-GB';
  return new Intl.DateTimeFormat(userLocale, option).format(date);
}

function getISODateTime(date, time) {
  const format = `${date}T${time}`;
  const newDate = new Date(format);
  return newDate.toISOString();
}

function extractDateAndTime(dateTime) {
  if (!dateTime) return ['', ''];

  const gmtString = new Date(dateTime);
  const date = gmtString.getDate();
  const month = gmtString.getMonth();
  const year = gmtString.getFullYear();
  // const time = gmtString.toLocaleTimeString().substring(0, 5);
  const time = gmtString.toString().substring(16, 21);

  const fillZero = (n) => {
    return n < 10 ? '0' + n : n;
  };
  const dateFormat = year + '-' + fillZero(month + 1) + '-' + fillZero(date);

  // const isoString = new Date(dateTime).toISOString();
  // const date = isoString.split('T')[0];
  // const time = isoString.split('T')[1].substring(0, 5);

  return [dateFormat, time];
}

export { getLocaleDateTime, getISODateTime, extractDateAndTime };
