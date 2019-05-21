export function flattenObject(obj) {
  return Object.entries(obj)
    .reduce((r, [key, value]) => {
      if (typeof value === 'object') {
        return { ...r, ...flattenObject(value) }
      }
      return { ...r, [key]: value }
    }, {})
}