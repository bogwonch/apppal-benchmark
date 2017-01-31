def prelude(out)
  out << "'x' says 'x' isInstallable.\n"
  out << "'user' says '0' can-say inf X isInstallable.\n"
end

def prelude_string(out)
  out << "\\'x\\' says \\'x\\' isInstallable.\n"
  out << "\\'user\\' says \\'0\\' can-say inf X isInstallable.\n"
end


def make_line(out, n, forking)
  forking.times do |f|
    out << "'#{n}' says '#{(n*forking)+(forking==1?1:f)}' can-say inf X isInstallable.\n"
  end
end

def make_lines(out, target, forking)
  limit = ((target+forking) / forking).to_i
  limit.times { |i| make_line out, i, forking }
end

def make_string_lines(out, target, forking)
  limit = ((target+forking) / forking).to_i
  limit.times do |n|
    forking.times do |f|
      out << "\\'#{n}\\' says \\'#{(n*forking)+(forking==1?1:f)}\\' can-say inf X isInstallable.\n"
    end
  end
end

def make_policy(target, forking)
  file = "%d-%d.policy" % [forking, target]
  open(file, 'w') do |out|
    prelude out
    make_lines out, target, forking
  end
end

def make_string_policy(out, target, forking)
  out << %Q{<string name="policy_#{forking}_#{target}">\n}
  prelude_string out
  make_string_lines out, target, forking
  out << "</string>\n"
end

def make_strings(out)
  make_string_policy out, 10,   1
  make_string_policy out, 100,  1
  make_string_policy out, 500,  1
  make_string_policy out, 1000, 1
  make_string_policy out, 10,   2
  make_string_policy out, 100,  2
  make_string_policy out, 500,  2
  make_string_policy out, 1000, 2
  make_string_policy out, 10,   3
  make_string_policy out, 100,  3
  make_string_policy out, 500,  3
  make_string_policy out, 1000, 3
end

make_strings STDOUT
# make_policy 10, 1
# make_policy 100, 1
# make_policy 500, 1
# make_policy 1000, 1
# make_policy 10, 2
# make_policy 100, 2
# make_policy 500, 2
# make_policy 1000, 2
# make_policy 10, 3
# make_policy 100, 3
# make_policy 500, 3
# make_policy 1000, 3
