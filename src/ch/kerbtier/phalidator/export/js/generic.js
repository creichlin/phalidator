var phalidator = {
  ph: {
    'in': function (value, set) {
      if(typeof set == 'string' || set instanceof String) {
        set = set.split("").filter(function(item, pos, self) {
          return self.indexOf(item) == pos;
        });
      }
      
      if(value instanceof Array) {
        return value.every(function(v) {
          return set.indexOf(v) > -1;
        });
      } else {
        return set.indexOf(value) != -1;
      }
    },
    
    range: function(from, to) {
      var values = [];
      if(to > from) {
        for(var c = from; c <= to; c++) {
          values.push(c);
        }
      } else {
        for(var c = to; c >= from; c--) {
          values.push(c);
        }
      }
      return values;
    },
    
    matches: function(str, regexp) {
      var match = str.match(new RegExp(regexp));
      return match != null && str == match[0];
    },
    
    letters: function(str) {
      return str.split("").filter(function(item, pos, self) {
        return self.indexOf(item) == pos;
      });
    }
  },
  
  isValid: function(name, entity, model) {
    var entity = phalidatorData[name].ns[entity];
    for(var rule in entity.rules) {
      var func = entity.rules[rule];
      if(!func(model, phalidator.ph)) {
        return false;
      }
    }
    return true;;
  },
  
  getInvalidRules: function(name, entity, model) {
    var entity = phalidatorData[name].ns[entity];
    var invalid = [];
    for(var rule in entity.rules) {
      var func = entity.rules[rule];
      if(!func(model, phalidator.ph)) {
        invalid.push(rule);
      }
    }
    return invalid;
  }

};