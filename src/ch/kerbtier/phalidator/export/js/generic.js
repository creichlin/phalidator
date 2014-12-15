var phalidator = {
  ph: {
    'in': function () {
      return false;
    },
    range: function() {
      return [1, 2, 3, 4];
    },
    matches: function() {
      return false;
    },
    letters: function() {
      return ['a', 'b', 'c'];
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