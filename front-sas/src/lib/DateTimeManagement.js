function getLocaleDateTime(dateTime) {
  const date = new Date(dateTime);
  const option = {
    day: "numeric",
    month: "short",
    year: "numeric",
    hour: "2-digit",
    minute: "2-digit",
  };
  const userLocale = navigator.language || "en-US";
  return new Intl.DateTimeFormat(userLocale, option).format(date);
}

export { getLocaleDateTime };


